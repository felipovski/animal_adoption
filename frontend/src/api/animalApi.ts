import axios from 'axios';

const API_URL = 'http://localhost:8082/animals';

export interface AnimalDto {
    id?: number;
    name: string;
    description: string;
    category: 'DOG' | 'CAT';
    status: 'AVAILABLE' | 'ADOPTED';
    birthDate?: string;
}

export const getAnimals = async (): Promise<AnimalDto[]> => {
    const response = await axios.get<AnimalDto[]>(API_URL);
    return response.data;
};

export const getAnimalById = async (id: number): Promise<AnimalDto> => {
    const response = await axios.get<AnimalDto>(`${API_URL}/${id}`);
    return response.data;
};

export const createAnimal = async (animal: AnimalDto): Promise<string> => {
    const response = await axios.post(API_URL, animal);
    return response.headers.location;
};

export const updateAnimal = async (id: number, animal: AnimalDto): Promise<AnimalDto> => {
    const response = await axios.put<AnimalDto>(`${API_URL}/${id}`, animal);
    return response.data;
};

export const deleteAnimal = async (id: number): Promise<void> => {
    await axios.delete(`${API_URL}/${id}`);
};
