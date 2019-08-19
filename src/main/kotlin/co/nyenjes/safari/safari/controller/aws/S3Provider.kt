package co.nyenjes.safari.safari.controller.aws

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GetObjectRequest
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import java.io.IOException

private val logger = KotlinLogging.logger {}

class S3Provider : S3Service {
    @Autowired
    private val s3client: AmazonS3? = null

    override fun downloadFile(keyName: String) {
        try {
            println("Downloading an object")
            val s3object = s3client!!.getObject(GetObjectRequest("safari-app", keyName))
            System.out.println("Content-Type: " + s3object.getObjectMetadata().getContentType())
            logger.info("===================== Import File - Done! =====================")

        } catch (ase: AmazonServiceException) {
            logger.info("Caught an AmazonServiceException from GET requests, rejected reasons:")
            logger.info("Error Message:    " + ase.message)
            logger.info("HTTP Status Code: " + ase.statusCode)
            logger.info("AWS Error Code:   " + ase.errorCode)
            logger.info("Error Type:       " + ase.errorType)
            logger.info("Request ID:       " + ase.requestId)
        } catch (ace: AmazonClientException) {
            logger.info("Caught an AmazonClientException: ")
            logger.info("Error Message: " + ace.message)
        } catch (ioe: IOException) {
            logger.info("IOE Error Message: " + ioe.message)
        }

    }

    override fun uploadFile(keyName: String, uploadFilePath: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}