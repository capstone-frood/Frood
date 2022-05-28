from urllib import response
from flask import Flask, request, jsonify
from tensorflow.keras.preprocessing.image import img_to_array
from tensorflow.keras.models import load_model
from keras.applications.imagenet_utils import preprocess_input
from keras.preprocessing import image
from PIL import Image
from io import BytesIO
import numpy as np
import json
import os

app = Flask(__name__)

model = load_model("KangkungSawi.h5")

ALLOWED_EXTENSIONS = {'jpg','jpeg'}

def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


def predicted(theImage):
    # prediction = model.predict(img)
    # prediction_string = [str(d) for d in prediction]
    return 1


@app.route('/')
def welcome():
    return "Hello World!"
  

@app.route('/predict',methods=['POST'])
def predict():
    images = request.files['image']
    if images and allowed_file(images.filename):
        dataImagePath='./images/'+images.filename
        images.save(dataImagePath)
        theImage=image.load_img(dataImagePath,target_size=(224,224))
        theImage = image.img_to_array(theImage)
        theImage=theImage.reshape((1, theImage.shape[0], theImage.shape[1], theImage.shape[2]))
        theImage= preprocess_input(theImage)
        result = predicted(theImage)
        # os.remove(dataImagePath)
        # response_json = {
        #     "prediction": list(result)
        # }
        # return json.dumps(response_json)

        if result == 1:
            Status = "Fresh"
            expired = "7 Days (Refrigirator temps)"
            Note = "Cook it before rotten"

        elif result==2:
            label = "Dog"
        # return json.dumps({'name': 'Pisces',
        #                'Result': label})
        return json.dumps({'Status' : Status,'Expired':expired,
        'Note':Note})
    else:
        resp = jsonify({'message': 'Image extension is not allowed'})
        resp.status_code = 400
        return resp

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=80)