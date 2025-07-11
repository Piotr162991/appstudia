import './App.css';
import {BrowserRouter as Router,Route, Routes} from "react-router-dom";
import PrivateRoutes from "./PrivateRoutes";
import PublicRoutes from "./PublicRoutes";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/logged/*" element={<PrivateRoutes />} />
                <Route path="/*" element={<PublicRoutes />} />
            </Routes>
        </Router>
    );
}

export default App;
