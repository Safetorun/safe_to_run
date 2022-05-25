package com.safetorun.builder

import com.google.common.truth.Truth.assertThat
import com.safetorun.inputverification.builders.model.UrlConfiguration
import com.safetorun.inputverification.builders.model.UrlConfigurations
import com.squareup.kotlinpoet.CodeBlock
import org.junit.Test

class UrlConfigurationCodeGenTest {

    @Test
    fun `test that url configuration generated nothing if not configuration is passed`() {
        val generatedCode = generateUrlListInitialisationCode(listOf())
        assertThat(generatedCode.toString()).isEmpty()
    }

    @Test
    fun `test that url configuration generates code for each list item`() {
        val generatedCode = generateUrlListInitialisationCode(
            listOf(
                UrlConfigurations("Test_1", UrlConfiguration.empty()),
                UrlConfigurations("Test_2", UrlConfiguration.empty())
            )
        ) {
            CodeBlock.builder().addStatement(it.name).build()
        }

        assertThat(generatedCode.toString()).isEqualTo("""
            Test_1
            Test_2
         
          """.trimIndent())
    }


}
