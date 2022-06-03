from flask import Flask, request, jsonify
from tensorflow.keras.preprocessing.image import img_to_array
from tensorflow.keras.models import load_model
import numpy as np
from PIL import Image
from io import BytesIO

class Model:
    def load_model(self):
        model_path = 'Model_10_Class_Acc99.h5'
        self.model = self.load_model(model_path)

    def preprocessing(image):
        img = Image.open(BytesIO(image))
        img = img.resize((224, 224), Image.ANTIALIAS)
        img = img_to_array(img)
        img = np.expand_dims(img, axis=0)
        return img

    def predict(self,image):
        model = self.model
        img = self.preprocessing(image)
        prediction = model.predict(img)
        result = np.argmax(prediction, axis=1)
        if result == 0:
            Status = "Fresh Apple"
            expired = "12 Days (Refrigirator temps)"
            Note = "Eat it quickly"
        elif result ==1:
            Status = "Fresh Banana"
            expired = "12 Days (Refrigirator temps)"
            Note = "Eat it quickly"
        elif result ==2:
            Status = "Fresh Orange"
            expired = "30 Days (Refrigirator temps)"
            Note = "Eat it slowly, you still have a lot of time"
        elif result ==3:
            Status = "Fresh Strawberry"
            expired = "10 Days (Refrigirator temps)"
            Note = "Eat it quickly"
        elif result ==4:
            Status = "Fresh Tomato"
            expired = "20 Days (Refrigirator temps)"
            Note = "Eat it slowly, you still have a lot of time"
        elif result ==5:
            Status = "Spoil Apple"
            expired = "Already Expired"
            Note = "Throw it away"
        elif result ==6:
            Status = "Spoil Banana"
            expired = "Already Expired"
            Note = "Don't ever eat that"
        elif result ==7:
            Status = "Spoil Orange"
            expired = "Already Expired"
            Note = "Remember to never eat that"
        elif result ==8:
            Status = "Spoil Strawberry"
            expired = "Already Expired"
            Note = "Don't ever think about eating it"
        elif result ==9:
            Status = "Spoil Tomato"
            expired = "Already Expired"
            Note = "Throw it away"
        return jsonify({'Status' : Status,'Expired':expired,'Note':Note})