# Houseplants-Server
## The API for the companion project to *The Beginner's Guide to Programming (in Clojure!)*

Got a lot of houseplants? Generate a watering schedule--in Clojure! You will run this API and communicate with it to create your watering schedule using [The Houseplant Helper](https://github.com/mtyzz/houseplant-helper).

## What you will need to install to run this
Follow the links to download and install instructions for these common tools.
- [Github CLI](https://github.com/cli/cli)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker-compose](https://docs.docker.com/compose/install/)
- [Postgres](https://www.postgresql.org/download/)
- [Clojure, if you haven't already](https://clojure.org/guides/getting_started)

## How to start this server
1. run this command in the projects directory: `gh repo clone mtyzz/houseplants-server`. That will put a copy of this code onto your computer.
2. Start Docker according to the instructions for your OS.
3. from `[YOUR PROJECT DIRECTORY]/houseplants-server`, run `docker-compose up -d`. This will start your postgres database.
4. run `clj -M:run`. You should see the words `Creating new plants table with data` and `Starting server` on your console. Until you kill the program, your server will run. You can visit [localhost:4000/](localhost:4000/) and you should see an interactive list of the endpoints and their expected parameters on the screen. You can try out the different endpoints right there! You can also visit [localhost:4000/plants](localhost:4000/plants) and you will see a list of plants. Your server is running and you are ready to use it to make your houseplants schedule!
