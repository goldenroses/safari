package co.nyenjes.safari.safari.controller.aws

interface S3Service {
        fun downloadFile(keyName: String)
        fun uploadFile(keyName: String, uploadFilePath: String)
}