
string = str(raw_input())
new_str = ''.join(e for e in string if e.isalnum())
new_str = new_str.lower()
if new_str == new_str[::-1]:
    return 1
else: return 0
