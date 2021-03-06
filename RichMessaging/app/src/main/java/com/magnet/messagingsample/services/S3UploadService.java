package com.magnet.messagingsample.services;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.File;
import java.util.UUID;


public class S3UploadService {

    private static final String TAG = "S3UploadService";
    private static final String AWS_S3_BUCKETNAME = "richmessagebucket"; // AWS bucket name
    private static final String AWS_IDENTITY_POOL_ID = ""; // AWS Cognito Identity Pool Id
    private static final Regions AWS_REGION = Regions.US_EAST_1;
    private static final String PREFIX = "magnet_test"; // bucket folder name

    private static AmazonS3Client mS3Client;
    private static TransferUtility sTransferUtility = null;

    /**
     * Initializes the AWS S3 credentials provider and transfer manager
     */
    public static void init(Context context) {
        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                context.getApplicationContext(), // Context
                AWS_IDENTITY_POOL_ID, // Identity Pool ID
                AWS_REGION // Region
        );
        mS3Client = new AmazonS3Client(credentialsProvider);
        sTransferUtility = new TransferUtility(mS3Client, context);
    }

    /**
     * Shuts down this client object, releasing any resources that might be held open
     */
    public static void destroy() {
        mS3Client.shutdown();
    }

    /**
     * Generates a key for use with the external storage provider.  This could also just
     * be a unique path/filename.
     */
    public static String generateKey(File file) {
        String suffix = "";
        String path = file.getPath();
        int idx = path.lastIndexOf('.');
        if (idx >= 0) {
            suffix = path.substring(idx);
        }
        return PREFIX + "/" + UUID.randomUUID().toString() + suffix;
    }

    /**
     * Builds a URL for the file to be retrieved from the external provider.  In this example for Amazon S3,
     * it is based off of a bucket name and a key
     */
    public static String buildUrl(String key) {
        //construct the publicly accessible url for the file
        //in this case, we have our bucket name and the key
        return "https://s3-us-west-2.amazonaws.com/" + AWS_S3_BUCKETNAME + "/" + key;
    }

    /**
     * Uploads the file to the external storage provider
     */
    public static void uploadFile(String key, File file, TransferListener listener) {
        final TransferObserver uploadObserver = sTransferUtility.upload(AWS_S3_BUCKETNAME, key, file);
        uploadObserver.setTransferListener(listener);
    }
}
