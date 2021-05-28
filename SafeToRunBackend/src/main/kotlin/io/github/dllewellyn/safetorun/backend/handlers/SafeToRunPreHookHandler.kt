package io.github.dllewellyn.safetorun.backend.handlers

import com.amazonaws.services.codedeploy.AmazonCodeDeployClientBuilder
import com.amazonaws.services.codedeploy.model.LifecycleEventStatus
import com.amazonaws.services.codedeploy.model.PutLifecycleEventHookExecutionStatusRequest
import com.amazonaws.services.lambda.AWSLambdaClientBuilder
import com.amazonaws.services.lambda.invoke.LambdaFunction
import com.amazonaws.services.lambda.invoke.LambdaInvokerFactory
import io.github.dllewellyn.safetorun.features.installorigin.GooglePlayStore
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.github.dllewellyn.safetorun.models.models.deviceInformationBuilder
import io.micronaut.context.annotation.Prototype
import io.micronaut.core.annotation.Introspected
import io.micronaut.function.aws.MicronautRequestHandler
import org.slf4j.LoggerFactory

@Introspected
@Prototype
class SafeToRunPreHookHandler : MicronautRequestHandler<Map<String, String>, Unit>() {

    override fun execute(input: Map<String, String>) {
        Logger.debug(input.toString())

        val lambdaFunctions = LambdaInvokerFactory.builder()
            .lambdaClient(AWSLambdaClientBuilder.defaultClient())
            .build(LambdaFunctions::class.java)

        val result = lambdaFunctions.doSomeStuff(buildDto())
        val status = if (result.signature.isNotEmpty()) {
            LifecycleEventStatus.Succeeded
        } else {
            LifecycleEventStatus.Failed
        }

        AmazonCodeDeployClientBuilder.defaultClient()
            .putLifecycleEventHookExecutionStatus(
                PutLifecycleEventHookExecutionStatusRequest()
                    .withDeploymentId(input["DeploymentId"])
                    .withLifecycleEventHookExecutionId(input["LifecycleEventHookExecutionId"])
                    .withStatus(status)
            )
    }

    private fun buildDto() = deviceInformationBuilder("ApiKey") {
        deviceId("DeviceId")
        installedApplication("com.a.b.c")
        installOrigin(GooglePlayStore().originPackage)
        osVersion("31")
        manufacturer("Google")
        signature("")
    }

    private interface LambdaFunctions {
        @LambdaFunction(functionName = "SafeToRun")
        fun doSomeStuff(request: DeviceInformationDto?): DeviceSignature
    }

    private class DeviceSignature {
        var signature: String = ""
    }

    companion object {
        private val Logger = LoggerFactory.getLogger(SafeToRunPreHookHandler::class.java)
    }
}
