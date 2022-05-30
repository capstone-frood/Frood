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
import numpy as np
import os
from werkzeug.utils import secure_filename
from keras.utils import load_img,img_to_array

app = Flask(__name__)

ALLOWED_EXTENSIONS = {'jpg','jpeg'}


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

def resize(img_path,image):
    img = load_img(img_path, target_size=(224, 224))
    img = img_to_array(img)
    # img = Image.open(BytesIO(image))
    # img = img.resize((224, 224), Image.ANTIALIAS)
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
        basepath = os.path.dirname(__file__)
        file_path = os.path.join(
            basepath, 'images', secure_filename(image.filename))
        image.save(file_path)
        image = image.read()
        img = resize(file_path,image)
        result = predicted(img)
        if result == 1:
            Status = "Fresh"
            expired = "7 Days (Refrigirator temps)"
            Note = "Cook it before rotten"

        return jsonify({'Status' : Status,'Expired':expired,'Note':Note})
    else:
        resp = jsonify({'message': 'Image extension is not allowed'})
        resp.status_code = 400
        return resp

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)