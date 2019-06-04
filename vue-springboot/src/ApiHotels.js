import axios from 'axios'  
  
const SERVER_URL = 'http://localhost:8089';  
  
const instance = axios.create({  
  baseURL: SERVER_URL,  
  timeout: 1000  
});  
  
export default {  
  // (R)ead  
  getAll: () => instance.get('hotels', {  
    transformResponse: [function (data) {  
      return data? JSON.parse(data)._embedded.hotels : data;  
    }]  
  })
}