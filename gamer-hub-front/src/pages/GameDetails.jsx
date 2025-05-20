import { useContext, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

// context
import APIContext from "../context/APIContext.jsx";

// componenti
import Loader from "../components/Loader.jsx";

// custom hook
import usePageTitle from "../hooks/PageTitle.jsx";

export default function GameDetails() {
    const { gameDetails, fetchGameById, loading } = useContext(APIContext);
    const { id } = useParams();
    const numericId = Number(id);

    const navigate = useNavigate();

    useEffect(() => {
        if (!gameDetails || gameDetails.id !== numericId) {
            fetchGameById(numericId);
        }
    }, [numericId, gameDetails]);

    usePageTitle(gameDetails?.name ? `GamerHub - ${gameDetails.name}` : "Loading...");

    if (loading) {
        return <Loader />;
    }

    return (
        <div className="col">
            <button onClick={() => navigate(-1)} className="btn btn-sm bg-accent2">
                Back to games
            </button>
            {gameDetails ? (
                <>
                    <h1>{gameDetails.name}</h1>
                    <div className="card bg-card">
                        <img src={gameDetails.url} alt={gameDetails.url} />
                        <p className="txt-primary">
                            Description: {gameDetails.description}
                        </p>
                        <p className="txt-primary">
                            Price: {gameDetails.price}€
                        </p>

                        {/* sezione saldi */}
                        {gameDetails.sales?.length > 0 ?
                            (
                                <>
                                    <p className="txt-primary">
                                        Active sales:
                                    </p>
                                    <ul>
                                        {gameDetails.sales.map(sale => (
                                            <li key={sale.id}>
                                                <p className="txt-primary">
                                                    Name: {sale.title}
                                                </p>
                                                <p className="txt-primary">
                                                    Starts: {sale.startDate}
                                                </p>
                                                <p className="txt-primary">
                                                    Finishes: {sale.finishDate}
                                                </p>
                                            </li>
                                        ))}
                                    </ul>
                                </>

                            ) : (
                                <p className="txt-primary">
                                    No sales available
                                </p>
                            )}

                        {/* sezione piattaforme */}
                        {gameDetails.platforms?.length > 0 ?
                            (
                                <>
                                    <p className="txt-primary">
                                        Platforms:
                                    </p>
                                    <ul>
                                        {gameDetails.platforms.map(platform => (
                                            <li key={platform.id}>
                                                <p className="txt-primary">
                                                    {platform.name}
                                                </p>
                                            </li>
                                        ))}
                                    </ul>
                                </>

                            ) : (
                                <p className="txt-primary">
                                    No platforms available yet
                                </p>
                            )}

                        {/* sezione tags */}
                        {gameDetails.tags?.length > 0 ?
                            (
                                <>
                                    <p className="txt-primary">
                                        Tags:
                                    </p>
                                    <ul>
                                        {gameDetails.tags.map(tag => (
                                            <li key={tag.id}>
                                                <p className="txt-primary">
                                                    {tag.name}
                                                </p>
                                            </li>
                                        ))}
                                    </ul>
                                </>

                            ) : (
                                <p className="txt-primary">
                                    No tags available yet
                                </p>
                            )}
                    </div>
                </>
            ) : (
                <p>
                    Game not found
                </p>
            )}
        </div>
    );
}
