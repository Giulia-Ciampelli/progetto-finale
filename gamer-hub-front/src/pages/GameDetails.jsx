import { useContext, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";

// context
import APIContext from "../context/APIContext.jsx";

// componenti
import Loader from "../components/Loader.jsx";

// custom hook
import usePageTitle from "../hooks/PageTitle.jsx";

export default function GameDetails() {
    const { gameDetails, fetchGameById, loading, setTitle } = useContext(APIContext);
    const { id } = useParams();
    const numericId = Number(id);
    const navigate = useNavigate();

    useEffect(() => {
        if (!gameDetails || gameDetails.id !== numericId) {
            fetchGameById(numericId);
        }
    }, [numericId, gameDetails]);

    useEffect(() => {
        if (gameDetails) {
            setTitle(`Details - ${gameDetails.name}`);
        }
    })

    usePageTitle(gameDetails?.name ? `GamerHub - ${gameDetails.name}` : "Loading...");

    if (loading) {
        return <Loader />;
    }

    return (
        <div className="col-12 mt-3">
            <button onClick={() => navigate(-1)} className="btn btn-sm bg-accent2">
                Back to games
            </button>
            {gameDetails ? (
                <>
                    <div className="card p-4 my-3 bg-card">
                        <div className="row">
                            <div className="col-md-6">
                                <img className="img-fluid" src={gameDetails.url} alt={gameDetails.url} />
                            </div>
                            <div className="col-md-6">
                                <div className="card-body">
                                    <h1 className="txt-primary">
                                        {gameDetails.name}
                                    </h1>
                                    <p className="card-text txt-primary mt-4">
                                        Description: {gameDetails.description}
                                    </p>
                                    <p className="card-text txt-primary">
                                        Price: {gameDetails.price}€
                                    </p>

                                    {/* sezione saldi */}
                                    {gameDetails.sales?.length > 0 ? (
                                        <div className="card p-4 my-3 bg-success">
                                            <p className="card-title fw-bold txt-primary">
                                                Active sales:
                                            </p>
                                            <ul>
                                                {gameDetails.sales.map(sale => (
                                                    <li className="list-unstyled" key={sale.id}>
                                                        <p className="card-text txt-primary">
                                                            Name: {sale.title}
                                                        </p>
                                                        <p className="card-text txt-primary">
                                                            Starts: {sale.startDate}
                                                        </p>
                                                        <p className="card-text txt-primary">
                                                            Finishes: {sale.finishDate}
                                                        </p>
                                                    </li>
                                                ))}
                                            </ul>
                                        </div>
                                    ) : (
                                        <div className="card p-4 my-3 bg-divider">
                                            <p className="card-title fw-bold txt-warning">
                                                No sales available
                                            </p>
                                        </div>
                                    )}

                                    {/* sezione piattaforme */}
                                    {gameDetails.platforms?.length > 0 ?
                                        (
                                            <div className="card p-4 my-3 bg">
                                                <p className="card-title fw-bold txt-primary">
                                                    Platforms:
                                                </p>
                                                <ul>
                                                    {gameDetails.platforms.map(platform => (
                                                        <li className="list-unstyled badge bg-accent2 mx-1" key={platform.id}>
                                                            <p className="card-text txt-dark">
                                                                {platform.name}
                                                            </p>
                                                        </li>
                                                    ))}
                                                </ul>
                                            </div>
                                        ) : (
                                            <div className="card p-4 my-3 bg-divider">
                                                <p className="card-title fw-bold txt-warning">
                                                    No platforms available yet
                                                </p>
                                            </div>
                                        )}

                                    {/* sezione tags */}
                                    {gameDetails.tags?.length > 0 ?
                                        (
                                            <div className="card p-4 my-3 bg">
                                                <p className="card-title fw-bold txt-primary">
                                                    Tags:
                                                </p>
                                                <ul>
                                                    {gameDetails.tags.map(tag => (
                                                        <li className="list-unstyled badge bg-accent1 mx-1" key={tag.id}>
                                                            <p className="card-text txt-dark">
                                                                {tag.name}
                                                            </p>
                                                        </li>
                                                    ))}
                                                </ul>
                                            </div>
                                        ) : (
                                            <div className="card p-4 my-3 bg-divider">
                                                <p className="card-title fw-bold txt-warning">
                                                    No platforms available yet
                                                </p>
                                            </div>
                                        )}
                                </div>
                            </div>
                        </div>
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
