# Read About:
curl -i -H "Content-Type: application/json" -X GET localhost:8080/about

# Create First Game:
curl -i -H 'Content-Type: application/json' -X POST -d '{
        "description": "My first game!"
    }' localhost:8080/games

# Create Second Game:
curl -i -H 'Content-Type: application/json' -X POST -d '{
        "description": "Second game"
    }' localhost:8080/games

# List all games:
curl -i -H 'Content-Type: application/json' -X GET localhost:8080/games

# Read one game:
curl -i -H 'Content-Type: application/json' -X GET localhost:8080/games/1


curl -i -H 'Content-Type: application/json' -X GET localhost:8080/games/1/board


# add moves
# add move 1

curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "X",  "row": 0, "col": 0
    }' localhost:8080/games/1/moves


# add move 2
curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "O",  "row": 1, "col": 0
    }' localhost:8080/games/1/moves


# add move 3

curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "X",  "row": 1, "col": 1
    }' localhost:8080/games/1/moves

# add move 4
curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "O",  "row": 0, "col": 1
    }' localhost:8080/games/1/moves

# add move 5
curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "X",  "row": 2, "col": 2
    }' localhost:8080/games/1/moves


#see allmoves
curl -i -H 'Content-Type: application/json' -X GET localhost:8080/games/1/moves

#see board
curl -i -H 'Content-Type: application/json' -X GET localhost:8080/games/1/board

#see game1
curl -i -H 'Content-Type: application/json' -X GET localhost:8080/games/1
