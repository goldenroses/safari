package co.nyenjes.safari.safari.controller

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.*
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import jdk.nashorn.internal.runtime.ScriptingFunctions.readLine
import org.springframework.web.bind.annotation.PathVariable
import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.IOException
import java.util.stream.Stream


private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/aws")
class AwsController {

    final val credentials: AWSCredentials = BasicAWSCredentials(
        System.getenv("aws-access-key-id"),
        System.getenv("aws-secret-access-key")
    )
    var s3client = AmazonS3ClientBuilder
        .standard()
        .withCredentials(AWSStaticCredentialsProvider(credentials))
        .withRegion(Regions.US_WEST_2)
        .build()

    @GetMapping("/listbuckets")
    fun listAWSBuckets(): MutableList<Bucket>? {
        val buckets = s3client.listBuckets()

        try {
            for (bucket in buckets) {
                logger.debug { "Bucket: ${bucket}" }
            }
        } catch (e: Exception) {
            logger.debug { "Exception caught: ${e}" }
        }
        return buckets
    }

    @GetMapping("/buckets/bucketName/{bucketName}/key/{key}")
    fun getJsonValues(@PathVariable bucketName: String, @PathVariable key: String): String? {
        val s3object = s3client.getObject(GetObjectRequest(bucketName, key+".json"))
        logger.debug {  "getContentType : ${s3object.getObjectMetadata().getContentType()}"}

        var reader = BufferedReader(InputStreamReader(s3object.getObjectContent(), "UTF-8"))

        val content = reader.readText()

       return Gson().toJson(content)
    }

    @GetMapping("/buckets/bucketName/{bucketName}/folderName/{folderName}")
    fun getImagesInFolder(@PathVariable bucketName: String,@PathVariable folderName: String): MutableSet<S3ObjectSummary> {
        val listObjectsInBucket = ListObjectsRequest().withBucketName(bucketName).withPrefix(folderName + "/")

        val s3object = s3client.listObjects(listObjectsInBucket)
        logger.debug {  "getContentType : ${s3object}"}

        return s3object.objectSummaries.toMutableSet()
    }
}