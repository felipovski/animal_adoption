import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import CreateAnimalForm from './components/CreateAnimalForm';
import UpdateAnimalForm from './components/UpdateAnimalForm';

const App: React.FC = () => {
    return (
        <Router>
            <div>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/create" element={<CreateAnimalForm />} />
                    <Route path="/update/:id" element={<UpdateAnimalForm />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;
