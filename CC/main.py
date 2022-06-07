from flask import Flask, request, jsonify
from tensorflow.keras.preprocessing.image import img_to_array
from tensorflow.keras.models import load_model
import numpy as np
from PIL import Image
from io import BytesIO
import os

app = Flask(__name__)

ALLOWED_EXTENSIONS = {'jpg','jpeg'}
model_path = 'model/Model_18X_Class_Acc99.h5'
model = load_model(model_path)


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

def resize(image):
    img = Image.open(BytesIO(image))
    img = img.resize((224, 224), Image.ANTIALIAS)
    img = img_to_array(img)
    img = np.expand_dims(img, axis=0)
    return img

@app.route('/')
def index():
    return "Hello World"

@app.route('/predict',methods=['POST'])
def predict():
    image = request.files['image']
    if image and allowed_file(image.filename):
        image = image.read()
        img = resize(image)
        results = model.predict(img)
        max_value = np.max(results)
        if max_value<=0.8:
            return jsonify({'Status' : "-",'Expired':"-",'Note':"Image can not be predicted"})
        elif max_value>0.8:
            result = np.argmax(results, axis=1)
            if result == 0:
                Status = "Fresh Apple"+" ({:.0%})".format(max_value)
                expired = "12 Days (Refrigirator temps)"
                Note = "Eat it quickly"
            elif result ==1:
                Status = "Fresh Banana"+" ({:.0%})".format(max_value)
                expired = "12 Days (Refrigirator temps)"
                Note = "Eat it quickly"
            elif result ==2:
                Status = "Fresh Chili"+" ({:.0%})".format(max_value)
                expired = "5-6 Days (Refrigirator temps)"
                Note = "Buy it immedietly"
            elif result ==3:
                Status = "Fresh Kangkung"+" ({:.0%})".format(max_value)
                expired = "3 Days (Refrigirator temps)"
                Note = "Buy it immedietly"
            elif result ==4:
                Status = "Fresh Orange"+" ({:.0%})".format(max_value)
                expired = "30 Days (Refrigirator temps)"
                Note = "Eat it slowly, you still have a lot of time"
            elif result ==5:
                Status = "Fresh Sawi"+" ({:.0%})".format(max_value)
                expired = "2-5 Days (Refrigirator temps)"
                Note = "Buy it immedietly"
            elif result ==6:
                Status = "Fresh Spinach"+" ({:.0%})".format(max_value)
                expired = "1 Days (Refrigirator temps)"
                Note = "Cook it immediately after you buy it"
            elif result ==7:
                Status = "Fresh Strawberry"+" ({:.0%})".format(max_value)
                expired = "10 Days (Refrigirator temps)"
                Note = "Eat it quickly"
            elif result ==8:
                Status = "Fresh Tomato"+" ({:.0%})".format(max_value)
                expired = "20 Days (Refrigirator temps)"
                Note = "Eat it slowly, you still have a lot of time"
            elif result ==9:
                Status = "Spoil Apple"+" ({:.0%})".format(max_value)
                expired = "Already Expired"
                Note = "Throw it away"
            elif result ==10:
                Status = "Spoil Banana"+" ({:.0%})".format(max_value)
                expired = "Already Expired"
                Note = "Don't ever eat that"
            elif result ==11:
                Status = "Spoil Chili"+" ({:.0%})".format(max_value)
                expired = "Already Expired"
                Note = "Throw it away immediately in the trashcan"
            elif result ==12:
                Status = "Spoil Kangkung"+" ({:.0%})".format(max_value)
                expired = "Already Expired"
                Note = "Please don't cook and eat"
            elif result ==13:
                Status = "Spoil Orange"+" ({:.0%})".format(max_value)
                expired = "Already Expired"
                Note = "Remember to never eat that"
            elif result ==14:
                Status = "Spoil Sawi"+" ({:.0%})".format(max_value)
                expired = "Already Expired"
                Note = "Please don't cook and eat"
            elif result ==15:
                Status = "Spoil Spinach"+" ({:.0%})".format(max_value)
                expired = "Already Expired"
                Note = "The spinach is no longer fit for consumption"
            elif result ==16:
                Status = "Spoil Strawberry"+" ({:.0%})".format(max_value)
                expired = "Already Expired"
                Note = "Don't ever think about eating it"
            elif result ==17:
                Status = "Spoil Tomato"+" ({:.0%})".format(max_value)
                expired = "Already Expired"
                Note = "Throw it away"
            return jsonify({'Status' : Status,'Expired':expired,'Note':Note})
    else:
        resp = jsonify({'message': 'Image extension is not allowed'})
        resp.status_code = 400
        return resp

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)