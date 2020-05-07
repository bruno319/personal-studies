import sys
import urllib2
import json
import os
from contextlib import closing

if __name__ == '__main__':
    slack_url = sys.argv[1]
    processes = os.popen("ps -e -o pid= -o comm=").read()

    slack_headers = {'Content-type': 'application/json'}
    data = {"text": processes}
    request = urllib2.Request(slack_url, json.dumps(data), headers=slack_headers)
    with closing(urllib2.urlopen(request, timeout=5)) as response:
        print(response.read())
