import './App.css';
import ConformOtp from './components/ConformOtp';
import ForgotPassword from './components/ForgotPassword';
import Home from './components/Home';
import Login from './components/Login';
import NoPage from './components/NoPage';
import Register from './components/Register';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <header className="App-header">

        <Router>
          <Routes>

            <Route path="login" element={<Login />} />
            <Route path="register" element={<Register />} />
            <Route path="/home" element={<Home />} />
            <Route path="/forgot-password" element={<ForgotPassword />} />
            <Route path="/conform" element={<ConformOtp />} />
            <Route path="*" element={<NoPage />} />
          </Routes>
        </Router>
      </header>
    </div>
  );
}

export default App;
