# Rich Messaging

We created a simple Android chat app to demonstrate how rich content such as images, videos, and geographical location can be delivered and received using Magnet Message. Images and videos are uploaded to Amazon S3, and the URL to the file is delivered to the recipient. This app also demonstrates Facebook integration with Magnet Message. 

## Features

* Registration and login through Magnet Message
* Login with Facebook 
* Obtain a list of users to chat with
* One to one chat with a user
* Send and receive text, pictures, videos, or a map pointing out your current location
* Select and upload images and video (via Amazon S3) to be viewed by your recipient
* Obtain your current geographical location, and send the coordinates to your receipient to be viewed as a map
* Receive notifications from other users which show up in your notification bar

## Video Walkthrough

![Video Walkthrough](android-rich-messaging-sample-2.gif)

## Installation

1. To send images and video, you will need to create an Amazon S3 bucket to store your uploaded files. Edit `message-samples-android/RichMessaging/app/src/main/java/com/magnet/messagingsample/services/S3UploadService.java` and replace the variables with your AWS Cognito Identity Pool Id, bucket name, folder name, and AWS region in `AWS_IDENTITY_POOL_ID`, `AWS_S3_BUCKETNAME`, `PREFIX` and `AWS_REGION`, respectively.
2. Since this app uses Facebook for Android, you will need to follow the instructions on the [Facebook Developer Getting Started](https://developers.facebook.com/docs/android/getting-started/) page to configure the Rich Messaging app to use your own Facebook developer account. In summary, you will need to create a Facebook app, import your own `facebook_app_id` into the Rich Messaging app, and set up the Development Key Hash for your development machine at Facebook.

All users of this app will be able to see and communicate with each other. If you would like to try this sample out privately, it is recommended that you create your own Magnet Message app by following the instructions at [Creating Your First App](https://docs.magnet.com/message/android/creating-your-first-app-android/). Once you obtain a `.properties` file, you can import it into the Rich Messaging app.

## How To Use

1. Enable GPS on your emulator or mobile device.
2. From the Login screen, type in a username and password, and click `Register`. Otherwise, log in with Facebook.
3. From the User Select screen, select a user to chat with. You can select your own username or `echo_bot` to send messages to yourself. 
4. From the Rich Messaging screen, try sending all the different types of rich content.