from flask import Flask, Response

app = Flask(__name__)

@app.route('/live.flv')
def live_flv():
    def generate():
        with open('video.flv', 'rb') as f:
            while True:
                data = f.read(1024)
                if not data:
                    break
                yield data
    return Response(generate(), mimetype='video/x-flv')

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
