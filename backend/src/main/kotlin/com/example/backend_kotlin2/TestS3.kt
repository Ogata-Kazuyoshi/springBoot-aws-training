//import software.amazon.awssdk.core.ResponseBytes
//import software.amazon.awssdk.regions.Region
//import software.amazon.awssdk.services.s3.S3Client
//import software.amazon.awssdk.services.s3.model.GetObjectRequest
//import software.amazon.awssdk.services.s3.model.GetObjectResponse
//import software.amazon.awssdk.services.s3.model.S3Exception
//import java.io.File
//import java.io.FileOutputStream
//import java.io.IOException
//import java.io.OutputStream
//
///**
// * Before running this Java V2 code example, set up your development environment, including your credentials.
// *
// * For more information, see the following documentation topic:
// *
// * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
// */
//object GetObjectData {
//    @JvmStatic
//    fun main(args: Array<String>) {
//        val usage = """
//
//            Usage:
//                <bucketName> <keyName> <path>
//
//            Where:
//                bucketName - The Amazon S3 bucket name.
//                keyName - The key name.
//                path - The path where the file is written to.
//
//            """.trimIndent()
//
//        if (args.size != 3) {
//            println(usage)
//            System.exit(1)
//        }
//
//        val bucketName = args[0]
//        val keyName = args[1]
//        val path = args[2]
//        val region: Region = Region.US_EAST_1
//        val s3: S3Client = S3Client.builder()
//            .region(region)
//            .build()
//
//        getObjectBytes(s3, bucketName, keyName, path)
//    }
//
//    fun getObjectBytes(s3: S3Client, bucketName: String?, keyName: String?, path: String?) {
//        try {
//            val objectRequest: GetObjectRequest = GetObjectRequest
//                .builder()
//                .key(keyName)
//                .bucket(bucketName)
//                .build()
//
//            val objectBytes: ResponseBytes<GetObjectResponse> = s3.getObjectAsBytes(objectRequest)
//            val data: ByteArray = objectBytes.asByteArray()
//
//            // Write the data to a local file.
//            val myFile = File(path)
//            val os: OutputStream = FileOutputStream(myFile)
//            os.write(data)
//            println("Successfully obtained bytes from an S3 object")
//            os.close()
//        } catch (ex: IOException) {
//            ex.printStackTrace()
//        } catch (e: S3Exception) {
//            System.err.println(e.awsErrorDetails().errorMessage())
//            System.exit(1)
//        }
//    }
//}