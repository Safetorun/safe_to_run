package com.safetorun.intents.url

import com.google.common.truth.Truth.assertThat
import com.safetorun.intents.url.params.AllowedType
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class UrlConfigTest {

    @Test
    fun `test that allow any url will pass`() {
        assertThat(URL.verify {
            allowAnyUrl()
        }).isTrue()
    }

    @Test
    fun `test that allow any url with paramswill pass`() {
        assertThat("$URL?query=abc".verify {
            allowAnyUrl()
        }).isTrue()
    }

    @Test
    fun `test that url config will pass if allowed host`() {
        assertThat(
            URL.verify {
                HOST.allowHost()
            }
        ).isTrue()
    }

    @Test
    fun `test that url config will pass if allowed url`() {
        assertThat(
            URL.verify {
                URL.allowUrl()
            }
        ).isTrue()
    }

    @Test
    fun `test that url config will fail by default`() {
        assertThat(
            URL.verify {}
        ).isFalse()
    }

    @Test
    fun `test that url config will pass if parameters are allowed`() {
        assertThat(
            URL_WITH_QUERY_PARAM.verify {
                HOST.allowHost()
                allowParameter {
                    parameterName = QUERY_PARAM
                }
            }
        ).isTrue()
    }

    @Test
    fun `test that url config will pass if parameters and type is allowed`() {
        assertThat(
            URL_WITH_QUERY_PARAM.verify {
                HOST.allowHost()
                allowParameter {
                    parameterName = "queryparam"
                    allowedType = AllowedType.Boolean
                }
            }
        ).isTrue()
    }

    @Test
    fun `test that url config will fail if parameters and type is not allowed`() {
        assertThat(
            URL_WITH_QUERY_PARAM.verify {
                HOST.allowHost()
                allowParameter {
                    parameterName = QUERY_PARAM
                    allowedType = AllowedType.Double
                }
            }
        ).isFalse()
    }

    @Test
    fun `test that url config will fail if parameters are not allowed`() {
        assertThat(
            URL_WITH_QUERY_PARAM.verify {
                HOST.allowHost()
            }
        ).isFalse()
    }

    @Test
    fun `test that url config will pass if all parameters are  allowed`() {
        assertThat(
            URL_WITH_QUERY_PARAM.verify {
                HOST.allowHost()
                allowAnyParameter()
            }
        ).isTrue()
    }

    @Test
    fun `test that url with config will be ok if the url itself is allowed`() {
        assertThat(URL_WITH_QUERY_PARAM.verify {
            URL_WITH_QUERY_PARAM.allowUrl()
        }).isTrue()
    }

    @Test
    fun `test that url config will fail with @ if all parameters are  allowed`() {
        assertThat(
            "$URL@def.com".verify {
                HOST.allowHost()
                allowAnyParameter()
            }
        ).isFalse()
    }

    @Test
    fun `test that url config will verify the underlying URL configuration (passes)`() {
        assertThat(
            "$URL?proceed_to=$URL?blah=blah".verify {
                HOST.allowHost()

                allowParameter {
                    parameterName = "proceed_to"
                    allowedType = AllowedType.Url {
                        HOST.allowHost()
                        allowAnyParameter()
                    }
                }
            }
        ).isTrue()
    }

    @Test
    fun `test that url config will verify the underlying URL configuration (fail)`() {
        assertThat(
            "$URL?proceed_to=https://google.co.uk".verify {
                HOST.allowHost()

                allowParameter {
                    parameterName = "proceed_to"
                    allowedType = AllowedType.Url {
                        HOST.allowHost()
                    }
                }
            }
        ).isFalse()
    }


    companion object {
        private const val HOST = "abc.com"
        const val URL = "https://$HOST"
        const val QUERY_PARAM = "queryparam"
        const val URL_WITH_QUERY_PARAM = "$URL?$QUERY_PARAM=false"
    }
}
