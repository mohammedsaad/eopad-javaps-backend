# eopad-javaps-backend

## Dev environment setup

1. init the `javaps-rest` submodule: `git submodule update --init javaps-rest/`
2. run maven (e.g. `mvn clean install` or via an IDE)
3. build the Docker image: `docker build -f local-build.Dockerfile -t eopad-javaps:latest .`
4. run the Docker container (linux): `docker run -p 8080:8080 -v /var/run/docker.sock:/var/run/docker.sock --env-file .env eopad-javaps:latest`
5. run the Docker container (windows): `docker run -p 8080:8080 -v //var/run/docker.sock:/var/run/docker.sock --env-file .env eopad-javaps:latest`

Processing backend for javaPS containing processes for Testbed-15 EOPAD Thread

```json
{
   "inputs":[
      {
         "id":"INPUT_SOURCE",
         "input":{
            "format":{
               "mimeType":"text/plain"
            },
            "value": "S2A_MSIL2A_20190629T103031_N0212_R108_T32UNB_20190629T135351"
         }
      }
   ],
   "outputs":[
      {
         "id":"OUTPUT_RASTER",
         "format":{
            "mimeType":"text/plain"
         },
         "transmissionMode":"value"
      }
   ]
}
```
