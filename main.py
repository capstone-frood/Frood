# from flask import Flask, request, jsonify
# # from tensorflow.keras.preprocessing.image import img_to_array
# # from tensorflow.keras.models import load_model
# # from keras.applications.imagenet_utils import preprocess_input
# # from keras.preprocessing import image
# # import numpy as np
# # import json

# app = Flask(__name__)

# # ALLOWED_EXTENSIONS = {'jpg','jpeg'}

# # def allowed_file(filename):
# #     return '.' in filename and \
# #            filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

# # def predicted(theImage):
# #     # prediction = model.predict(img)
# #     # prediction_string = [str(d) for d in prediction]
# #     return 1


# @app.route('/')
# def welcome():
#     return "Hello World!"
  

# # @app.route('/predict',methods=['POST'])
# # def predict():
# #     images = request.files['image']
# #     if images and allowed_file(images.filename):
# #         dataImagePath='./images/'+images.filename
# #         images.save(dataImagePath)
# #         theImage=image.load_img(dataImagePath,target_size=(224,224))
# #         theImage = image.img_to_array(theImage)
# #         theImage=theImage.reshape((1, theImage.shape[0], theImage.shape[1], theImage.shape[2]))
# #         theImage= preprocess_input(theImage)
# #         result = predicted(theImage)
# #         # os.remove(dataImagePath)
# #         # response_json = {
# #         #     "prediction": list(result)
# #         # }
# #         # return json.dumps(response_json)

# #         if result == 1:
# #             Status = "Fresh"
# #             expired = "7 Days (Refrigirator temps)"
# #             Note = "Cook it before rotten"
# #         # return json.dumps({'name': 'Pisces',
# #         #                'Result': label})
# #         return json.dumps({'Status' : Status,'Expired':expired,
# #         'Note':Note})
# #     else:
# #         resp = jsonify({'message': 'Image extension is not allowed'})
# #         resp.status_code = 400
# #         return resp

# if __name__ == '__main__':
#     app.run(host='0.0.0.0', port=8080)


from flask import Flask, request, jsonify
from tensorflow.keras.preprocessing.image import img_to_array
from tensorflow.keras.models import load_model
import numpy as np
from PIL import Image
from io import BytesIO
import os

app = Flask(__name__)

ALLOWED_EXTENSIONS = {'jpg','jpeg'}

model = load_model(Model_10_Class_Acc99.h5)


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

def resize(image):
    img = Image.open(BytesIO(image))
    img = img.resize((224, 224), Image.ANTIALIAS)
    img = np.array(img) / 255
    img = np.expand_dims(img, axis=0)
    return img

def predicted(img):
    return 1

@app.route('/',methods=['GET'])
def index():
    return "Hello World"

@app.route('/predict',methods=['POST'])
def predict():
    image = request.files['image']
    if image and allowed_file(image.filename):
        image = image.read()
        img = resize(image)
        result = model.predict(img)
        if result == 0:
            Status = "Fresh Apple"
            expired = "7 Days (Refrigirator temps)"
            Note = "Cook it before rotten"
        elif result ==1:
            Status = "Fresh Banana"
            expired = "2 Days (Refrigirator temps)"
            Note = "Cook it before rotten"
        elif result ==2:
            Status = "Fresh Orange"
            expired = "8 Days (Refrigirator temps)"
            Note = "Eat it quickly"
        elif result ==3:
            Status = "Fresh Strawberry"
            expired = "21 Days (Refrigirator temps)"
            Note = "Eat it quickly"
        elif result ==4:
            Status = "Fresh Tomato"
            expired = "12 Days (Refrigirator temps)"
            Note = "Eat it quickly"
        elif result ==5:
            Status = "Spoil Apple"
            expired = "1 Days (Refrigirator temps)"
            Note = "Eat it quickly"
        elif result ==6:
            Status = "Spoil Banana"
            expired = "1 Days (Refrigirator temps)"
            Note = "Don't eat if you want to be healthy"
        elif result ==7:
            Status = "Spoil Orange"
            expired = "2 Days (Refrigirator temps)"
            Note = "Eat it quickly"
        elif result ==8:
            Status = "Spoil Strawberry"
            expired = "2 Days (Refrigirator temps)"
            Note = "Eat it quickly"
        elif result ==9:
            Status = "Spoil Tomato"
            expired = "12 Days (Refrigirator temps)"
            Note = "Eat it quickly"
            
        return jsonify({'Status' : Status,'Expired':expired,'Note':Note})
    else:
        resp = jsonify({'message': 'Image extension is not allowed'})
        resp.status_code = 400
        return resp

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)