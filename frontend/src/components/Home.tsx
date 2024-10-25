import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getAnimals, deleteAnimal, AnimalDto } from '../api/animalApi';
import './Home.css';

const Home: React.FC = () => {
    const navigate = useNavigate();
    const [animals, setAnimals] = useState<AnimalDto[]>([]);

    // Carrega os animais ao montar o componente
    useEffect(() => {
        loadAnimals();
    }, []);

    // Função para carregar os animais da API
    const loadAnimals = async () => {
        const data = await getAnimals();
        setAnimals(data);
    };

    // Função para deletar o animal
    const handleDelete = async (id: number) => {
        if (window.confirm("Are you sure you want to delete this animal?")) {
            await deleteAnimal(id);
            loadAnimals(); // Atualiza a lista após a deleção
        }
    };

    return (
        <div className="home-container">
            <h1>Animal Adoption</h1>
            <p>Select an action for each animal:</p>
            <div className="animal-list">
                {animals.length > 0 ? (
                    <ul>
                        {animals.map((animal) => (
                            <li key={animal.id}>
                                {animal.name} - {animal.category}
                                {/* Garante que o ID está sendo tratado como número */}
                                <button onClick={() => navigate(`/update/${animal.id}`)} className="home-button">Update</button>
                                <button onClick={() => handleDelete(animal.id!)} className="home-button delete-button">Delete</button>
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>No animals available</p>
                )}
            </div>
            <button onClick={() => navigate('/create')} className="home-button">Create New Animal</button>
        </div>
    );
};

export default Home;
