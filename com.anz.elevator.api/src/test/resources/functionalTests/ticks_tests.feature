Feature: Test 'tick' API
  Description: This is to test the tick API which moves elevators in a building one step

  Background: A building with two elevators one moving up and the other moving down
    Given Building has 2 elevators with top floor at 20 and bottom floor at 0
    Given Elevator 1 is at level 10 moving down with its door closed and plans to stop at floors:
      | 2 | 3 | 7 |
    Given Elevator 2 is at level 2 moving up with its door closed and plans to stop at floors:
      | 8 | 13 | 17 |

    Scenario: Move the elevators one step
      When Elevator time ticks
      Then Elevator 1 is at level 9
      Then Elevator 2 is at level 3