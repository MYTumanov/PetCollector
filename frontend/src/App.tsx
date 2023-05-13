// import { useEffect, useState } from "react";
// import reactLogo from "./assets/react.svg";
// import viteLogo from "/vite.svg";
// import './reset.css';
import './app.css';
import { MainList } from './components/MainList/MainList';
import { UserCard } from './containers/UserCard/UserCard';

function App() {
  // const [debts, setDebts] = useState(["loading"]);

  // useEffect(() => {
  //   fetch(
  //     "http://localhost:8085/api/debt?userId=643715296f76850a69071023"
  //   ).then(async (response) => {
  //     const json = await response.json();
  //     setDebts([ `${json[0].debtors[0].userId} ${json[0].debtors[0].sum}`] )
  //     // console.log(`${json[0].debtors[0].userId} ${json[0].debtors[0].sum}`);
  //   });
  // }, []);

  return (
    <div className="app">
    </div>
  );
}

export default App;
