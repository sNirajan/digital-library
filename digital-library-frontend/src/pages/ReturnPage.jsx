import { useEffect, useState } from 'react';
import axios from 'axios';

function ReturnPage() {
  const [borrowings, setBorrowings] = useState([]);

  const userId = 1; // temporary placeholder

  const fetchBorrowings = () => {
    axios.get(`http://localhost:8080/borrowing/user/${userId}`)
      .then(res => setBorrowings(res.data))
      .catch(err => console.error("Error fetching borrowings:", err));
  };

  useEffect(() => {
    fetchBorrowings();
  }, []);

  const handleReturn = (borrowingId) => {
    axios.put(`http://localhost:8080/borrowing/return/${borrowingId}`)
      .then(() => {
        alert("Book returned!");
        fetchBorrowings(); // refresh list
      })
      .catch(err => {
        console.error("Return failed:", err);
        alert("Failed to return book.");
      });
  };

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <h1 className="text-3xl font-bold mb-6 text-center text-blue-800">My Borrowed Books</h1>

      {borrowings.length === 0 ? (
        <p className="text-center text-gray-600">No borrowed books.</p>
      ) : (
        <div className="space-y-4">
          {borrowings.map(b => (
            <div key={b.id} className="bg-white p-4 rounded shadow-md">
              <h2 className="text-xl font-semibold">{b.book.title}</h2>
              <p className="text-sm text-gray-600">Author: {b.book.author}</p>
              <p className="text-sm text-gray-600">Genre: {b.book.genre}</p>
              <p className="text-sm text-gray-600">Borrowed On: {b.borrowedDate}</p>

              <button
                className="mt-3 px-4 py-2 rounded bg-red-600 text-white hover:bg-red-700"
                onClick={() => handleReturn(b.id)}
              >
                Return
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default ReturnPage;
