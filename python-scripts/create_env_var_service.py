import os
from flask import Flask

app = Flask(__name__)


@app.route("/env/<env_name>/<env_var>", methods=['POST'])
def create_environment_variable(env_name, env_var):
    os.environ[env_name] = env_var
    return os.environ[env_name]


if __name__ == '__main__':
    app.run(port=8080, debug=True)
