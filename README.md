![alt text](https://github.com/capstone-frood/Frood/blob/d960b8776a9a0a493461cc525b7e44ff5dfac278/LOGO/1%20TRANS%20Resized.png "Frood Logo")
---
[Bangkit](https://grow.google/intl/id_id/bangkit/ "Bangkit Website") Capstone Project Application For Preventing Food Waste With Fresh And Spoilage Detection 

## Our Apps Is Build With
### :cloud: Cloud Computing
* [Flask API](https://flask.palletsprojects.com/en/2.1.x/)
* [Google Cloud Platform](https://cloud.google.com/gcp/)
* [Cloud Run](https://cloud.google.com/run/)
### :computer: Machine Learning
* [TensorFlow](https://www.tensorflow.org/)
* [Google Colab](https://research.google.com/colaboratory/)
* [Jupyter Notebook](https://jupyter.org/)
### :iphone: Mobile Development
* [Android Studio](https://developer.android.com/studio/preview)
* [Adobe XD](https://www.adobe.com/id_en/products/xd.html)
* [CanHub Image Cropper](https://github.com/CanHub/Android-Image-Cropper)

# Screenshot
## Splash Screen
![image](https://user-images.githubusercontent.com/99663816/172169730-faeb1838-708f-4246-a013-b03a4237db5b.png)
## Home
![image](https://user-images.githubusercontent.com/99663816/172169766-8276f2a3-c4c1-445e-954f-7ea965b1260e.png)
![image](https://user-images.githubusercontent.com/99663816/172169798-e53473c0-f4bd-4f23-99b5-a5ec14ac3971.png)

## Detect Screen
![image](https://user-images.githubusercontent.com/99663816/172170011-bf5de811-3173-4aa6-8bcd-a0d6da49936e.png)
![image](https://user-images.githubusercontent.com/99663816/172170125-921df25d-8ead-4b85-9f51-9e8414d7d746.png)

## Information
![image](https://user-images.githubusercontent.com/99663816/172169840-d695cd8d-705d-4654-83a3-a9e6c448bf1d.png)

# How To Use Our App
  1. Download and open the Frood application
  2. Click the detect button to scan the freshness of the ingredients
  3. In the detect button, click the select image to predict the ingredients (camera or gallery)
  4. After you choose the image, click next and the app will process your image
  5. Wait for the result page for the prediction
  6. Click close button to go back to home page 
  7. Click home page to get some information about ingredients
  8. Click the information page for tutorial on using the application
  9. Close the app

# Architecture


### :cloud: How To Deploy Flask API On Google Cloud Platform (Cloud Run)
  1. Create Flask API
  2. Create Dockerfile 
     ```Dockerfile
     ...
     FROM python:3.9.0
     # Copy local code to the container image
     COPY . /app

     # Sets the working directory
     WORKDIR /app

     # Upgrade PIP
     RUN pip install --upgrade pip

     #Install python libraries from requirements.txt
     RUN pip install --no-cache-dir -r requirements.txt

     # Set $PORT environment variable
     ENV PORT 8080

     # Run the web service on container startup
     CMD exec gunicorn --bind :$PORT --workers 1 --threads 8 --timeout 0 main:app
     ```
  3. Go to Google Cloud Platform (https://console.cloud.google.com/)
  4. Create a Project
  5. Click cloud shell to clone git
  6. Clone the git with this command
     ```Command
     ...
     git clone (github links https)
     ```
  7. Then type `ls` to see that we have sucess clone the github
  8. Change the directory to github, type `cd (directory that we want to use)`
  9. Clcik Terminal
  10. In Terminal, we click `cloud clone`
  11. Than we click `Deploy to Cloud Run`
  12. In  `Deploy to Cloud Run` we can settings configurations before deploy
  13. And than after we settings the configurations we clik `Deploy`

# Links
1. Citation Link (https://bit.ly/froodcitation)
2. Dataset (https://bit.ly/3NAsoVZ)
