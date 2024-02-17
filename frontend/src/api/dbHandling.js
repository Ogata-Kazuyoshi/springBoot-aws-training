import axiosClient from './axiosClient';

const dbApi = {
  getDB: () => axiosClient.get('data'),
  createCard: (id, params) => axiosClient.post(`/data/memo/${id}`, params),
  deletCard: (id) => axiosClient.delete(`/data/${id}`),
  updateCard: (id, params) => axiosClient.patch(`/data/memo/${id}`, params),
};

export default dbApi;
