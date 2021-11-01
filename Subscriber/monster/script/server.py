import socket 

def main():
   host = "127.0.0.1"
   port = 2872
   sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
   sock.connect((host,port))

   sock.send(b'\x01\x00\x10\x07victory\x07victory')
   print(sock.recv(1024))

if __name__ == "__main__":
    main()
