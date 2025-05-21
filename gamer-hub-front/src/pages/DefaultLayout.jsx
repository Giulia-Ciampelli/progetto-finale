import { Outlet } from "react-router-dom";

// componenti
import Header from "../components/Header.jsx";
import Footer from "../components/Footer.jsx";

export default function DefaultLayout() {
    return (
        <div className="layout">
            <Header />
            <main>
                <div className="container">
                    <div className="row mb-4">
                        <Outlet />
                    </div>
                </div>
            </main>
            <Footer />
        </div>
    )
}