Implement Game.java in accordance with the rules to complete the service. Once complete, start the service and use react-gameoflife to see the game running.

Run `./mvnw spring-boot:run` to start the service

### Rules to implement

- Any live cell with fewer than two live neighbors dies, as if by under population

- Any live cell with two or three live neighbors lives on to the next generation

- Any live cell with more than three live neighbors dies, as if by overpopulation

- Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction