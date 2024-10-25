import React, { useState } from 'react';
import { createAnimal, AnimalDto } from '../api/animalApi';

const CreateAnimalForm: React.FC = () => {
    const [animal, setAnimal] = useState<AnimalDto>({
        name: '',
        description: '',
        category: 'DOG',
        status: 'AVAILABLE'
    });

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        await createAnimal(animal);
        alert('Animal created successfully!');
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Create New Animal</h2>
            <input
                type="text"
                placeholder="Name"
                value={animal.name}
                onChange={(e) => setAnimal({ ...animal, name: e.target.value })}
                required
            />
            <input
                type="text"
                placeholder="Description"
                value={animal.description}
                onChange={(e) => setAnimal({ ...animal, description: e.target.value })}
                required
            />
            <select value={animal.category} onChange={(e) => setAnimal({ ...animal, category: e.target.value as 'DOG' | 'CAT' })}>
                <option value="DOG">Dog</option>
                <option value="CAT">Cat</option>
            </select>
            <select value={animal.status} onChange={(e) => setAnimal({ ...animal, status: e.target.value as 'AVAILABLE' | 'ADOPTED' })}>
                <option value="AVAILABLE">Available</option>
                <option value="ADOPTED">Adopted</option>
            </select>
            <button type="submit">Create</button>
        </form>
    );
};

export default CreateAnimalForm;
