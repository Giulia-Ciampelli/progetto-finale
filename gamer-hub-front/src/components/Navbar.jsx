import { NavLink } from "react-router-dom";

export default function Navbar() {
    return (
        <nav className="py-2 px-5">
            <NavLink to="/">
                Home
            </NavLink>
            <NavLink to="/games">
                Games
            </NavLink>
        </nav>
    )
}