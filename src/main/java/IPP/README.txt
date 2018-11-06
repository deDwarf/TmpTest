IPP Lab2: Structural Patterns

#1# Flyweight
Participant classes:
    IPP.Flyweight.*
    Flyweight - IPP.Flyweight.UnitAppearance
Context:
    Some game of strategy genre where an user has an ability to customize the units with some custom appearance
    and them spawn such customized units to the battlefield.
Problem:
    As we do not expect to see a lot of different kinds of appearance and we do expect multiple units with the same look
    to be spawned, keeping the appearance detail values directly inside unit objects would result in useless waste of memory.
Solution:
    Move all the appearance details to some separate objects (Flyweights) and pass them to units by reference


#2# Decorator
Participant classes:
    All classes from 'Decorator' and 'Flyweight' packages.
Context:

Problem:

Solution:

