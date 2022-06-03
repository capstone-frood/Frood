from flask import Flask, request, jsonify
from model.model import Model

app = Flask(__name__)

ALLOWED_EXTENSIONS = {'jpg','jpeg'}
model = Model() 

def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/')
def index():
    return "Hello World"

@app.route('/predict',methods=['POST'])
def predict():
    image = request.files['image']
    if image and allowed_file(image.filename):
        image = image.read()
        results = model.predict(image)
        return results
    else:
        resp = jsonify({'message': 'Image extension is not allowed'})
        resp.status_code = 400
        return resp

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)