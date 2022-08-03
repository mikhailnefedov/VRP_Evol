# VRP_Evol

Small JavaFX program to get the exam admission for C195 Evolutionäre Algorithmen

- Vehicle Routing Problem (basic version, without time, route length complexity, ...)

Sequence:

- Creates start generation
- individuals get their fitness (available fitness functions: longest route length, sum of all routes)
- parents are choosen (available parent selection: ranking based selection, tournament selection (1vs5)
- parents do a crossover (available crossover: order crossover, edge crossover)
- the new children may mutate (available mutation: swap mutation, inverting mutation)
- the individuals for the next generation are selected (available enivronment selection: Best x selection (x=population size), q round 2 fold tournament selection)


Notes:

- map was randomly generated
- orange point = hq (start/end point of truck route)
- longest route length works better as a fitness function, sum of all routes leads to only one truck route
- frontend doesn't update simultaneously with computation (no multithreading)
- The customers are the points on the scatter graph. While the program shows which truck delivers the package to which customer, it doesn't show in which order the deliveries happen. 

![screen_evolutionary_vrp](https://user-images.githubusercontent.com/52661281/182610613-1a4f6661-81ce-4fd8-9c63-e52ad2996581.PNG)
