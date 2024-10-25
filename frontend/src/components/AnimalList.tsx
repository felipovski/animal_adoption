import React, { useEffect, useState } from 'react';
import { getAnimals, AnimalDto } from '../api/animalApi';

const AnimalList: React.FC = () => {
    const [animals, setAnimals] = useState<AnimalDto[]>([]);

    useEffect(() => {
        loadAnimals();
    }, []);

    const loadAnimals = async () => {
        const data = await getAnimals();
        setAnimals(data);
    };

    return (
        <div>
            <h2>Animal List</h2>
            <ul>
                {animals.map((animal) => (
                    <li key={animal.id}>
                        {animal.name} - {animal.category}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default AnimalList;
