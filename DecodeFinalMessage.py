# This is the decoded message received when finishing the Level 5 challenge. :-)
# 
# Submitting solution...
# 
# With one last roar of the escape pod's engines, you and your bunny companions jump to lightspeed.
# 
# Congratulations! You've destroyed the LAMBCHOP, relieved the bunnies, gotten Commander Lambda off your tail, and saved the galaxy. Time for a little rest and relaxation back on Bunny Planet. Pat yourself on the back -- you've earned it!
# 
# Submission: SUCCESSFUL.
# 
# Completed in: 10 hrs, 54 mins, 0 secs..
# 
# <encrypted>b'C0YRGQwCCBweSFBbQksIEwgOGUhcQUUPAA0BCgwIBQRFTFVBSgoeGxUEDwkLRkFPSgoWBw0eGxJK\nT1dPVwgMDx0ECQYPAxVGTkxIAA4HBAoGBA8JARVKT1dPVxQMAAACBgoJSFxBRR4OAw8GGRxXQVhM\nSBIMCQhIXEFFCgAOSk9XT1cWCwJORhA='</encrypted>
#       
#     For your eyes only!
#     Use the status command to repeat this message.
# 

import base64
from itertools import cycle
import json

message = "C0YRGQwCCBweSFBbQksIEwgOGUhcQUUPAA0BCgwIBQRFTFVBSgoeGxUEDwkLRkFPSgoWBw0eGxJK\nT1dPVwgMDx0ECQYPAxVGTkxIAA4HBAoGBA8JARVKT1dPVxQMAAACBgoJSFxBRR4OAw8GGRxXQVhM\nSBIMCQhIXEFFCgAOSk9XT1cWCwJORhA="
key = "pabloamomo"
b64toASCII = base64.b64decode(message)
decoded = bytes(i ^ j for i, j in zip(b64toASCII, cycle(bytes(key, "utf8")))).decode('UTF-8');

print("---------------------------------------------------------------------")
print(json.dumps(json.loads(decoded.replace("'", "\"")), indent=2))
print("---------------------------------------------------------------------")

# Decoded Message
# {
#   "success": "great",
#   "colleague": "esteemed",
#   "efforts": "incredible",
#   "achievement": "unlocked",
#   "rabbits": "safe",
#   "foo": "win!"
# }
