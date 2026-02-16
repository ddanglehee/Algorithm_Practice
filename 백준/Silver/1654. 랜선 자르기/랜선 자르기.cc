#include <iostream>
#include <vector>
#include <algorithm>
typedef long long ll;
using namespace std;

int n, k;
vector<ll> v;

ll search(ll low, ll high){
    ll mid;
    ll sum;
    ll a;
    while(low <= high){
        mid = (low + high) / 2;
        sum = 0;
        for(int i = 0; i < v.size(); i++){
            sum += (v[i] / mid);
        }
        if(n <= sum){
            a = mid;
            low = mid + 1;
        }else{
            high = mid - 1;
        }
    }
    return a;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0), cout.tie(0);

    cin>> k>> n;

    ll tmp;
    ll m = 0;
    for(int i = 0; i < k; i++){
        cin>> tmp;
        v.push_back(tmp);
        if(m < tmp) m = tmp;
    }
    sort(v.begin(), v.end());
    ll ans = search(1, m);
    cout<< ans;
}