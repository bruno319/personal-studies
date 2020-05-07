import os
import json
from flask import Flask

app = Flask(__name__)


@app.route("/conf/env")
def list_all_environment_variables():
    return json.dumps(dict(os.environ))


if __name__ == '__main__':
    app.run(port=8080, debug=True)
