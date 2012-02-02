def check(a, b):
  if a != b and a[2:4] != b[2:4]: 
    result = "ok"
  else:
    result = "err"
  return result