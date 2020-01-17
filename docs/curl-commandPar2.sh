curl -i -H 'Content-Type: application/json' -X POST -d '{
        "description": "My first game!"
    }' localhost:8080/games



#echo 'Should fail: X Goes first'
#Should fail: X Goes first

curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "O",  "row": 0, "col": 0
    }' localhost:8080/games/1/moves


curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "X",  "row": 0, "col": 0
    }' localhost:8080/games/1/moves

+ echo 'Should fail: Player cannot go twice in a row'
Should fail: Player cannot go twice in a row


curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "X",  "row": 0, "col": 1
    }' localhost:8080/games/1/moves


curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "O",  "row": 0, "col": 1
    }' localhost:8080/games/1/moves



+ echo 'Should fail: Invalid move'
Should fail: Invalid move


curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "X",  "row": -1, "col": 0
    }' localhost:8080/games/1/moves

+ echo 'Should fail: Invalid move'
Should fail: Invalid move

curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "O",  "row": 3, "col": 0
    }' localhost:8080/games/1/moves



+ echo 'Should fail: Invalid move'
Should fail: Invalid move


curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "X",  "row": 0, "col": -1
    }' localhost:8080/games/1/moves


+ echo 'Should fail: Invalid move'
Should fail: Invalid move

curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "O",  "row": 0, "col": 3
    }' localhost:8080/games/1/moves


+ echo 'Should fail: invalid piece'
Should fail: invalid piece


curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "W",  "row": 0, "col": 0
    }' localhost:8080/games/1/moves


curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "X",  "row": 1, "col": 0
    }' localhost:8080/games/1/moves




+ echo 'Should fail: Duplicate location played'
Should fail: Duplicate location played


curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "O",  "row": 1, "col": 0
    }' localhost:8080/games/1/moves



curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "X",  "row": 2, "col": 2
    }' localhost:8080/games/1/moves


curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "O",  "row": 1, "col": 2
    }' localhost:8080/games/1/moves


curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "X",  "row": 2, "col": 1
    }' localhost:8080/games/1/moves

curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "O",  "row": 1, "col": 1
    }' localhost:8080/games/1/moves


curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "X",  "row": 2, "col": 0
    }' localhost:8080/games/1/moves



+ echo 'Should fail: Game over'
Should fail: Game over

curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "O",  "row": 1, "col": 0
    }' localhost:8080/games/1/moves


+ echo 'Should fail: Game over (or turn order)'
Should fail: Game over (or turn order)

curl -i -H 'Content-Type: application/json' -X POST -d '{
        "piece": "X",  "row": 0, "col": 2
    }' localhost:8080/games/1/moves


curl -i -H 'Content-Type: application/json' -X GET localhost:8080/games/1/board


+ echo 'Should fail: Game not found'
Should fail: Game not found

curl -i -H 'Content-Type: application/json' -X GET localhost:8080/games/9999

+ echo 'Should fail: Game not found'
Should fail: Game not found
curl -i -H 'Content-Type: application/json' -X GET localhost:8080/games/9999/moves
