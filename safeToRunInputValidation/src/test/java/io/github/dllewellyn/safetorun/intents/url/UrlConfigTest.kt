package io.github.dllewellyn.safetorun.intents.url

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.intents.url.params.AllowedType
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class UrlConfigTest {

    @Test
    fun `test that allow any url will pass`() {
        assertThat(URL.urlVerification {
            allowAnyUrl()
        }).isTrue()
    }

    @Test
    fun `test that allow any url with paramswill pass`() {
        assertThat("$URL?query=abc".urlVerification {
            allowAnyUrl()
        }).isTrue()
    }

    @Test
    fun `test that url config will pass if allowed host`() {
        assertThat(
            URL.urlVerification {
                HOST.allowHost()
            }
        ).isTrue()
    }

    @Test
    fun `test that url config will pass if allowed url`() {
        assertThat(
            URL.urlVerification {
                URL.allowUrl()
            }
        ).isTrue()
    }

    @Test
    fun `test that url config will fail by default`() {
        assertThat(
            URL.urlVerification {}
        ).isFalse()
    }

    @Test
    fun `test that url config will pass if parameters are allowed`() {
        assertThat(
            "$URL?$QUERY_PARAM=false".urlVerification {
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
            "$URL?$QUERY_PARAM=false".urlVerification {
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
            "$URL?$QUERY_PARAM=false".urlVerification {
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
            "$URL?$QUERY_PARAM=false".urlVerification {
                HOST.allowHost()
            }
        ).isFalse()
    }

    @Test
    fun `test that url config will pass if all parameters are  allowed`() {
        assertThat(
            "$URL?$QUERY_PARAM=false".urlVerification {
                HOST.allowHost()
                allowAnyParameter()
            }
        ).isTrue()
    }

    companion object {
        private const val HOST = "abc.com"
        const val URL = "https://$HOST"
        const val QUERY_PARAM = "queryparam"
    }
}
