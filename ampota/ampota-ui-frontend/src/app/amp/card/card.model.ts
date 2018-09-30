export class Page {
  totalPages: number;
  totalElements: number;
  content: Card[];
}

export class Card {
    scryfallId: string;
    oracleId: string;
    name: string;
    lang: string;
    usd: number;
    imageUris: ImageUris;
    manaCost: string;
    cmc: number;
    typeLine: string;
    oracleText: string;
    colors: string[];
    legalities: Legalities;
    rarity: string;
    artist: string;
}

export class ImageUris {
    small: string;
    normal: string;
    large: string;
    png: string;
    art_crop: string;
    border_crop: string;
}

export class Legalities {
    standard: string;
    modern: string;
    legacy: string;
    pauper: string;
    vintage: string;
    commander: string;
    duel: string;
}

