import React, { useState, useEffect } from "react";
import './App.css';

function App() {
  const [state, updateState] = useState({
    "elevators": [
      { "floor": 2, "direction": "UP", "doorIsOpen": false, "plan": [ 5 ] },
      { "floor": 0, "direction": null, "doorIsOpen": false, "plan": [ ] }
    ],
    "well": { "topFloor": 6, "bottomFloor": 0 }
  });

  const callApi = async (path) => {
    const response = await fetch("http://localhost:8080/api/" + path, {
        method: "POST",
        headers: {
          'Accept': 'application/json, text/plain',
          'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(state)
      });

    const result = await response.json();
    updateState(result);
  }

  const tick = async () => {
    callApi("tick");
  }

  const callElevator = (floor) => {
    callApi("call?floor=" + floor);
  }

  const pressElevatorButton = (elevator, floor) => {
    callApi("press_button?elevator="+elevator+"&floor="+floor);
  }

  useEffect(() => {
    const interval = setInterval(() => {
      tick();
    }, 2000)

    return () => clearInterval(interval)
  }, []);

  var floors = [];
  for (var i = state["well"]["topFloor"]; i >= state["well"]["bottomFloor"]; i--) {
      floors.push(i);
  }

  return (
    <div className="App">
      <header className="App-header">
        <h1>Elevator</h1>
      </header>
      <main>
        <table><tbody>
          { floors.map((floor) => (
            <tr key={floor}>
              <td>
                <button onClick={() => callElevator(floor)}>Call from floor: {floor}</button>
              </td>
              { state["elevators"].map((elevator) => (
                <td>
                  <img src={
                     elevator["doorIsOpen"] && elevator["floor"] == floor ? "open.jpg" : "close.jpg"
                   } className={ "elevator" + (elevator["floor"] == floor ? " elevator-is-here" : "") } />
                </td>
              )) }
            </tr>
          )) }
          <tr>
            <td></td>
            { Array.from({ length: state["elevators"].length }, (_, i) => i).map((elevatorIndex) => (
              <td>
                { floors.map((floor) => (
                  <button onClick={() => pressElevatorButton(elevatorIndex, floor)} className={
                    "key-pad" +
                    (state["elevators"][elevatorIndex]["floor"] == floor ? " key-pad-here" : (
                      state["elevators"][elevatorIndex]["plan"].includes(floor) ? " key-pad-planned" : ""
                    ))
                  }>{floor}</button>
                )) }
              </td>
            ))}
          </tr>
        </tbody></table>
      </main>
    </div>
  );
}

export default App;
