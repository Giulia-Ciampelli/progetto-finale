// #region importazioni
import { BrowserRouter, Routes, Route } from "react-router-dom";

// layout
import DefaultLayout from './pages/DefaultLayout.jsx';

// pagine
import Home from './pages/Home.jsx';
import GameList from './pages/GameList.jsx';
import GameDetails from './pages/GameDetails.jsx';

// global context
import { APIContextProvider } from './context/APIContext.jsx';

// stile
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';
// import 'bootstrap/dist/js/bootstrap.bundle.min.js'; in caso di modali o effetti scroll

// #endregion importazioni

function App() {
  return (
    <>
      <APIContextProvider>
        <BrowserRouter>
          <Routes>
            <Route element={<DefaultLayout />}>
              <Route path="/" element={<Home />} />
              <Route path="/games" element={<GameList />} />
              <Route path="/games/:id" element={<GameDetails />} />
            </Route>
          </Routes>
        </BrowserRouter>
      </APIContextProvider>
    </>
  )
}

export default App
