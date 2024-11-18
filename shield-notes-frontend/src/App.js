import { LandingPage } from './components/LandingPage';
import Navbar from './components/Navbar'
import { BrowserRouter as Router , Route , Routes } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Navbar/>
      <Routes>
        <Route path = "/" element = {<LandingPage/>}/>
      </Routes>


    </Router>
    
      
  );
}

export default App;
