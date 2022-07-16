package dev.ssouza.gettingstarteddynamodb

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import java.net.URI

@Configuration
class DynamoDbConfig {

    @Value("\${aws.key}")
    val key: String? = null
    @Value("\${aws.secret}")
    val secret: String? = null
    @Value("\${aws.region}")
    val region: String? = null
    @Value("\${aws.endpoint}")
    val endpoint: String? = null

    @Bean
    fun awsCredentials(): AwsBasicCredentials = AwsBasicCredentials.create(key, secret)

    @Bean
    fun dynamoDbAsyncClient(awsBasicCredentials: AwsBasicCredentials): DynamoDbAsyncClient =
        DynamoDbAsyncClient.builder()
            .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
            .region(Region.of(region))
            .endpointOverride(URI.create(endpoint!!))
            .build()
}