import { useContext, useState, useEffect } from "react";

// context
import APIContext from "../context/APIContext.jsx";

export default function SearchBar() {
    const { setGames } = useContext(APIContext);
    const [search, setSearch] = useState('');

    useEffect(() => {
        fetchGames(search);
    }, [search]);

    const fetchGames = (name) => {
        const url = name ? `http://127.0.0.1:8080/api/games?name=${encodeURIComponent(name)}` : 'http://127.0.0.1:8080/api/games';

        fetch(url)
            .then(res => res.json())
            .then(data => {
                setGames(data);
            })
            .catch(err => {
                console.error("Failed fetching games", err);
            })
    }

    return (
        <div className="mt-3">
            <input className="form-control" type="search" placeholder="Search games by name..." value={search} onChange={e => setSearch(e.target.value)} />
        </div>
    )
}