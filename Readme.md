                                                    
1. ClientConnectNode 에서 Client와 Socket을 연결한다.
2. 연결된 Client에서 Data를 받아서 SocketInNode에 Message객체를 통하여 Data와 ip, port를 전달한다.
3. SocketInNode는 Message객체를 URLNode에 전달하여 Data를 분석한다.
4. URLNode는 분석된 Data를 Message객체에 담아서 HTTPRequestNode에 전달한다.
5. HTTPRequestNode는 Message객체에 담긴 정보를 ems.nhnacadem.com 서버에 HTTP Request 형태로 변환 후 전달 및 응답을 받는다.
6. HTTPRequestNode는 응답받은 Data를 Message객체에 담아서 RequestMessgeMake Node에 전달한다.
7. RequestMessgeMake Node는 Message객체에 담긴 Data를 Client에 전달할 json형태로 변환한다.
8. RequestMessgeMake Node는 변환된 json형태의 Data를 Message객체에 담아서 SocketOutNode에 전달한다.
9. SocketOutNode는 Message객체에 담긴 Data를 Message객체에 담긴 ip, port를 통하여 Client에 전달한다.
10. 끝.
``` <^오^>//```