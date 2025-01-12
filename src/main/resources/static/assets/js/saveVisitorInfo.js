export async function saveVisitorInfo() {
    try {
        // Fetch IP address using a third-party API
        const ip = await getIPAddress();

        // Gather visitor details
        const visitorInfo = {
            ip: ip || 'AUTO', // Use 'AUTO' if IP could not be fetched
            platform: getPlatform(),
            timestamp: getFormattedTimestamp(), // Current date and time in a readable format
        };

        console.log('Visitor Info to Save:', visitorInfo); // Log the gathered visitor info

        // Send a POST request to the Visitor Tracker API
        const response = await fetch('https://itay-olivcovitz-portfolio.up.railway.app/api/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(visitorInfo), // Send JSON object
        });

        if (response.ok) {
            console.log('Visitor info saved successfully:', visitorInfo);
        } else {
            // Log the server's response if the request failed
            const errorMessage = await response.text();
            console.error(`Failed to save visitor info. Status: ${response.status}, Message: ${errorMessage}`);
        }
    } catch (error) {
        console.error('Error saving visitor info:', error);
    }
}

// Helper function to determine the platform from the User-Agent
function getPlatform() {
    const userAgent = navigator.userAgent.toLowerCase();
    if (userAgent.includes('iphone')) return 'iPhone';
    if (userAgent.includes('android')) return 'Android';
    if (userAgent.includes('windows')) return 'Windows PC';
    if (userAgent.includes('mac')) return 'Mac';
    return 'Other';
}

// Helper function to get the public IP address (Optional)
async function getIPAddress() {
    try {
        const response = await fetch('https://api.ipify.org?format=json');
        if (response.ok) {
            const data = await response.json();
            return data.ip; // Return the public IP address
        } else {
            console.error(`Failed to fetch IP address. Status: ${response.status}`);
        }
    } catch (error) {
        console.error('Error fetching IP address:', error);
    }
    return null; // Return null if IP fetching fails
}

// Helper function to format the current timestamp
function getFormattedTimestamp() {
    const date = new Date();
    return date.toISOString(); // ISO 8601 format (e.g., "2025-01-12T18:25:43.511Z")
}
